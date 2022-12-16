<template>
    <div class="property-detail">
    <div  v-bind:key="property.propertyId">
      <div class="image-detail-div">
      <img class="image-detail" v-bind:src="property.pictureLink" />
      </div>
      
      <h2>{{ property.address }} </h2>
      <p>Rent: ${{property.rentAmount}}.00 / month</p>
      <p>{{ property.propertyDescription }} </p>
      
    </div>
    </div>
</template>

<script>

import PropertiesService from '../services/PropertiesService.js'
export default {
  data(){
    return {
      property: {}
    }
  },
  created() {
    PropertiesService.getPropertiesByPropertyId(this.$route.params.propertyId)
    .then(
      (response) => {
        this.property=response.data;
      }
    );
  }
}
</script>

<style scoped>
.image-detail-div {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  padding-top: 20px; 
}
.image-detail {
  box-shadow: 5px 5px 10px rgb(80, 80, 80);
  border-radius: 20px;
  width: 600px;
}

h2 {
  display: flex;
  justify-content: center;
   
}
.property-detail {
background-color:  rgb(229, 226, 226);
padding-bottom: 20px;
}

</style>