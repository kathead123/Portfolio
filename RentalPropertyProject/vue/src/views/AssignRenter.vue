<template>
  <div class="background-assign-renter">
    <div class="assign-renter-pic">
    <img v-bind:src='property.pictureLink'/>
    </div>
  <div>
<renter-list/>
</div>


      
  </div>
</template>

<script>
import RenterList from '../components/RenterList.vue'
import PropertiesService from '../services/PropertiesService.js'
export default {
  components: { RenterList },
  data(){
    return {
      property: {}
    }
  },
  created() {
    PropertiesService.getPropertiesByPropertyId(this.$route.params.propertyId).then(
      (response) => {
        this.property=response.data;
      }
    );
  }
}

//this.$route.params.propertyId

</script>

<style scoped>
img{
  width: 600px;
  margin-top: 20px;
  border-radius: 20px;
  box-shadow: 5px 5px 10px rgb(80, 80, 80);
  
}
.assign-renter-pic {
  display: flex;
  justify-content: center;  
}
.background-assign-renter {
  padding-top: 10px;
  margin-top: 20px;
  background-color:rgb(236, 232, 232);
}

</style>