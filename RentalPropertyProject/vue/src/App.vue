<template>
  <div id="app">
  <router-link v-bind:to="{ name: 'home' }">
   <img src="..\src\assets\macabrent.png" class="header-image">
  </router-link>

    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''"> Login</router-link>
      <router-link v-bind:to="{name: 'landlord-properties' }" v-if="$store.state.user.authorities && $store.state.user.authorities[0].name==='ROLE_LANDLORD'">My Rentals </router-link>
    </div>
    <router-view />
    
  </div>
</template>




