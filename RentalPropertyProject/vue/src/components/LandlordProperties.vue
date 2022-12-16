<template>
  <div>
    <div class="property-listing">
        <div  v-for="property in properties" v-bind:key="property.propertyId" class="property">
        <!-- <router-link v-bind:to="{
                          name:'Messages', 
                          params:{
                            id:topic.id
                            }
                          }" > -->
          <div class="image-div">
          <img class="prop-image" v-bind:src="property.pictureLink" />
          </div>
          <h2>{{ property.address }} </h2>
          
          <router-link v-bind:to="{name:'list-renters', params:{propertyId: property.propertyId}}" v-if="!property.rented"> 
          <button class="button">Assign a renter</button>
          </router-link>

          <div v-if="property.rented">
          <div class="rent-status">
            
          Number Overdue: {{overdueInvoices(property.propertyId).length}} /
          Upcoming Invoice: {{upcomingInvoice(property.propertyId).dueDate}} /
          Paid: {{upcomingInvoice(property.propertyId).paid}}
          </div>
          
         



          </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserService from '@/services/UserService.js'
import PropertiesService from '@/services/PropertiesService.js'
import moment from 'moment'

export default {
    
  name: 'landlord-properties-list',
  data() {
    return {
      properties: [],
      invoices: []
    }
  },
  created() {
    PropertiesService.getPropertiesByLandlordUserId().then(
      (response) => {
        this.properties = response.data;
      }
    ),
    UserService.getInvoicesByLandlordUserId().then(
    (response) => {
      this.invoices = response.data;
    }
  )
  },
  

  methods: {
    overdueInvoices(propertyId) {
     return this.invoices.filter(
        (invoice) => {
        return !invoice.paid && invoice.propertyId === propertyId && moment(invoice.dueDate, "YYYY-MM-DD").diff(moment(), 'days') < 0 
        }); 
      },
         upcomingInvoice(propertyId) {
         return this.invoices.find(
        (invoice) => {
        return moment(invoice.dueDate, "YYYY-MM-DD").diff(moment(), 'days') >= 0 && invoice.propertyId === propertyId
        });
        
        
      }
      }


}
</script>

<style>

</style>