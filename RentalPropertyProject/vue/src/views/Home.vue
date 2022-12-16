<template>
  <div class="home">
    <div class="home-welcome">
    <img class="banner-image" src="https://slack-imgs.com/?c=1&o1=ro&url=https%3A%2F%2Fwww.designbolts.com%2Fwp-content%2Fuploads%2F2018%2F09%2FVector-Halloween-Haunted-House-Twitter-Google-Plus-Header-Banner-Cover-Photo-Image-2018.jpg">
    <h1>Don't Be Haunted By Regret. Find The Spirit of Your Next Home.</h1>
    </div>
    <div v-if="$store.state.user.authorities && $store.state.user.authorities[0].name==='ROLE_LANDLORD'">
    <landlord />
    </div>

    <div v-if="$store.state.user.authorities && $store.state.user.authorities[0].name==='ROLE_MAINTENANCE'" >
    <maintenance  />
    </div>

    <div v-if="$store.state.user.authorities && $store.state.user.authorities[0].name==='ROLE_RENTER'"  >
    <renter />
    </div>
<!--v-if="!$store.state.user.authorities"-->
    <div >
    <properties />
    </div> 

    
  </div>
</template>

<script>
import Landlord from '../components/Landlord.vue';
import Maintenance from '../components/Maintenance.vue';
import Renter from '../components/Renter.vue';
import Properties from '../components/Properties.vue';
export default {
  components: { Landlord, Renter, Maintenance, Properties },
  name: "home"
};
    
</script>
