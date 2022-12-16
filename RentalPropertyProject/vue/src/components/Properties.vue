<template>
<div>
  <div class="available-rentals-div">
  <h3>Available Rentals:</h3>
  </div>
  <div class="property-listing">
    
    <div  v-for="property in properties" v-bind:key="property.propertyId" class="property">
    <router-link v-bind:to="{
                      name:'property-detail', 
                      params:{
                        propertyId:property.propertyId
                        }
                      }" >
      <div class="image-div">
      <img class="prop-image" v-bind:src="property.pictureLink" />
      </div>
      </router-link>
      <h2>{{ property.address }} </h2>
      
      
    </div>
  </div>
  </div>
</template>

<script>
import PropertiesService from '@/services/PropertiesService.js'
export default {
    
  name: 'properties-list',
  data() {
    return {
      properties: []
    }
  },
  created() {
    PropertiesService.getProperties().then(
      (response) => {
        this.properties = response.data;
      }
    )
  }
}
</script>

<style>

</style>