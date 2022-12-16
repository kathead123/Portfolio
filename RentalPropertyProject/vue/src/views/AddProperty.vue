<template>
  <div class="container3">

      
      <form id="add-property-form" class="form" v-on:submit.prevent="addProperty">
        <div class="background">
      <h1 class="formHead">Submit A New Property</h1>

      <div class="alert alert-danger" role="alert" v-if="formErrors">
        {{ formErrorMsg }}
      </div>
      
      <div class="item">
      <label for="address" class="formField">Address: </label>
      <input
        type="text"
        id="address"
        class="form-control"
        placeholder="Address"
        v-model="property.address"
        required
        autofocus
      />
      </div>

      <div class="item">
      <label for="rentAmount" class="formField">Listing Price: </label>
      <input
        type="number"
        id="rentAmount"
        class="form-control"
        placeholder="Amount"
        v-model="property.rentAmount"
        required
      />
      </div>

      <div class="item">
      <label for="propertyDescription" class="formField">Property Description: </label>
      <input
        type="text"
        id="propertyDescription"
        class="form-control"
        placeholder="Description"
        v-model="property.propertyDescription"
        v-on:click="resize()"
        required
      />
      </div>

      <div class="item">
      <label for="pictureLink" class="formField">Picture Link: </label>
      <input
        type="text"
        id="pictureLink"
        class="form-control"
        placeholder="PictureLink"
        v-model="property.pictureLink"
        required
      />
      </div>
      
      <div class="item">
      <router-link :to="{ name: 'home' }"></router-link>
      <button id="add-property-submit" class="button" type="submit">
        Submit
      </button>
      </div>

      


    </div>
    </form>

    <div class="image3">
        <img src="https://media.istockphoto.com/id/1173109356/photo/haunted-house.jpg?s=170667a&w=0&k=20&c=mlCz9M_CuzSPngMG5SeK-zNZheRlPjScfsXnlWSCe6E=" />
      </div>
  </div>
</template>

<script>
import PropertiesService from '../services/PropertiesService.js'

export default {
    name: "add-property",
    
    data() {
    return {
      property: {
        address: '',
        rentAmount: '',
        propertyDescription: '',
        pictureLink: ''
      },
      formErrors: false,
      formErrorMsg: 'There were problems submitting this form.',
    }
},
    methods: {
         resize() {
            document.getElementById('propertyDescription').style.height = '100px';
        
    },

        addProperty() {
        PropertiesService
          .addProperty(this.property)
          .then((response) => {
            if (response.status === 201) {
              this.$router.push({
                path: '/'
              });
            }
          })
          .catch((error) => {
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
    }
}

</script>

<style scoped>
.image3 img{
  height: 60vh;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  box-shadow: 5px 5px 10px rgb(80, 80, 80);
  margin-top:22px;
}

.background{
  background-color: #a6c790;
}

.formHead{
  padding-top:116px;
}

#add-property-submit {
  font-size:23px;
}
.formField{
  font-size: 18px;
}

</style>
