<template>


  <div class="add-renter-form">
    <form class="form">
      <label for="renter">Assign a renter to this property: </label>

         
          <select v-model="renter.userId" name="renter" id="renter"
          class="form-control">
          <option selected>Select A Renter</option>
          <option  v-bind:value="renter.id" v-for="renter in renters"
            v-bind:key="renter.id"  >{{renter.name}}</option>
          </select>
          

          <label for="lease-start-date">Lease Start Date: </label>
          <input
            type="date"
            id="lease-start-date"
            class="form-control"
            placeholder="Lease Start Date"
            v-model="renter.leaseStartDate"
            required
          />
          

    
      
      <button class="button" 
      type="submit" v-on:click.prevent="addRenter">
        Add Renter
      </button>  
     

    </form>  

      
         
  </div>
</template>

<script>

import UserService from '@/services/UserService.js'
export default {
data() {
    return {
      renters: [],
      //json sent in post
      renter:{
        renterId: '',
        leaseStartDate: '',
        leaseEndDate: '',
        userId: ''
      },
      formErrors: false,
      formErrorMsg: 'There were problems submitting this form.',
    }
  },
  created() {
    UserService.getAllRenters().then(
      (response) => {
        this.renters = response.data;
      }
    )
  },
methods: {
  addRenter(){
    UserService.addRenter(this.renter, this.$route.params.propertyId)
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

  

}
</script>

<style scoped>
select, input {
margin-left: 10px;
margin-right: 10px;
margin-top: 20px;

}

form {
 margin-top: 30px;
 margin-left: 27%;
 font-size: 20px;
  
}


</style>