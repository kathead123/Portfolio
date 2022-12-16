<template>
  <div id="container2" class="text-center">


    <form class="form-signin" @submit.prevent="login">
      <div class="background">
      <h1 class="please-sign-in">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>

      <div class="item">
      <label for="username" class="sr-only">Username: </label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      </div>

      <div class="item">
      <label for="password" class="sr-only">Password: </label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </div>

      

      <div class="item">
      <button id= "sign-in" class="button" type="submit">Sign in</button>
      </div>

      <div class="item">
      <router-link class="need-account" :to="{ name: 'register' }">Need an account?</router-link>
      </div>
      </div>

    </form>

    <div class="image2">
    <img src="https://cdna.artstation.com/p/assets/images/images/050/221/924/large/marina-timakova-domic-marina.jpg?1654344354 " />
    </div>

  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
             //if statement for redirect
            if(this.$store.state.user.authorities && this.$store.state.user.authorities[0].name==='ROLE_LANDLORD'){
              this.$router.push("/landlord-properties");
            } else{
              this.$router.push("/");
            }
           
            
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>

#container2 {
  background-color: rgb(229, 226, 226);
  margin-top:12px;
  padding-bottom: 50px;
  padding-left: 200px;
}

.please-sign-in{
  font-size: 75px;
  padding-top:50px;

}

.sr-only {
  font-size: 30px;
}

.item{
  margin-bottom: 5%;
}

.need-account{
  font-size: 28px;
  margin-left: 28px;
}

#sign-in{
  font-size: 20px;
}

#sign-in.button{
  width: 225px;
  padding: 4px;
  
}

.image2 img{
  margin-top:51px;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  box-shadow: 5px 5px 10px rgb(80, 80, 80);
}



.background {
  background-color: #91b7ba;
  text-align: center;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
  margin-left: 25%;
  box-shadow: 5px 5px 10px rgb(80, 80, 80);
  height: 60vh;
}


</style>
