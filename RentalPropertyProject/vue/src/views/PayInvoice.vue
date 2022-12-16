<template>
 <div class="make-payment" v-if="currentInvoice">
   
      <h1>Payment Information: </h1>

      <h2 class= "amount-due">${{currentInvoice.amountDue}}.00</h2>

      <h2 class= "amount-due">Due On: {{formattedDueDate}}</h2>
      
      <div class="confirm-checkbox" >
         I Acknowledge That If Payment Is Not On Time I will be Exorcised From The Premises
      <input type="checkbox"/>
      </div>

      <div class="rent-button">
      <button v-on:click.prevent="payInvoice" type="submit" class="button">Submit</button>
      </div>
  </div>
</template>

<script>
import InvoicesService from '@/services/InvoicesService.js'
// eslint-disable-next-line no-unused-vars
import moment from 'moment'
export default {
data() {
    return {
      invoices: [],
      //json sent in post
      invoice:{
        
      },
      formErrors: false,
      formErrorMsg: 'There were problems submitting this form.',
    }
  },
  created() {
    InvoicesService.listInvoicesByUserId().then(
      (response) => {
        this.invoices = response.data;
      }
    )
  },
  //revisit this method later
methods: {
  payInvoice(){
    InvoicesService.payInvoice(this.$route.params.invoiceNumber)
    .then((response) => {
      if (response.status === 200) {
        this.$router.push({
                path: '/'
    });
  }
}).catch((error) => {
            const response = error.response;
            this.formErrors = true;
            if (response.status === 400) {
              this.formErrorMsg = 'Bad Request: Validation Errors';
            }
          }); 
      },
       clearErrors() {
      this.formErrors = false;
      this.formErrorMsg = 'There were problems submitting this form.';
  
    }
  },
  computed: {
    currentInvoice() {
     return this.invoices.find(
        (invoice) => {
        return !invoice.isPaid 
        }); 
      },
      formattedDueDate() {
        return moment(this.currentInvoice.dueDate, "YYYY-MM-DD").format("MMM Do YYYY")
      }
      }
  }



</script>

<style scoped>
.make-payment {
  background-color:rgb(236, 232, 232);
  border: solid 2px black;
  margin-top: 20px;
}
.amount-due {
  margin-bottom: 20px;
}
.confirm-checkbox {
  margin-bottom: 20px;
  font-size: 20px;
}
.rent-button button {
  font-size: 20px;
}

</style>