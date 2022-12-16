<template>
 <div v-if="currentInvoice">
    
      <h1>Your Next Rent Payment: </h1>

      <h2 class= "amount-due">${{currentInvoice.amountDue}}.00</h2>

      <h2 class= "amount-due">Due On: {{formattedDueDate}}</h2>
      
         
      <div class="rent-button">
      <router-link v-bind:to="{name:'pay-invoice', params:{invoiceNumber:currentInvoice.invoiceNumber}}" >
      <button class="pay-rent-button" >
        Pay My Rent
      </button>  
      </router-link> 
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
    InvoicesService.payInvoice(this.invoice, this.$route.params.propertyId)
    .then((response) => {
      if (response.status === 200) {
        this.$router.push({
                path: '/landlord-properties'
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

<style>

</style>