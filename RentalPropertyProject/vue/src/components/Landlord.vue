<template>
  <div>
      <p></p>
      <div class="add-property-link">
      <router-link v-bind:to="{name: 'add-property'}">Add Property Here</router-link>
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
    PropertiesService.getPropertiesByLandlordUserId().then(
      (response) => {
        this.properties = response.data;
      }
    )
  }
}
</script>

<style>

</style>