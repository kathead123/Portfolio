<template>
<div class="container">

  
    <form id="register"  class="form-register" @submit.prevent="register">

      <div class="background">
      <h1 class="create-account">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>

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
      <label for="password" class="sr-only">Confirm Password: </label>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      </div>

      <div class="item">
      <label class="sr-only" for="role">Choose a role: </label>
    <select name="role" id="role"
    class="form-control"
    v-model="user.role"
    >
    <option value="renter">Renter</option>
  <option value="landlord">Landlord</option>
</select>
</div>

<div class="item">
<label for="name" class="sr-only">Name: </label>
<input
        type="name"
        id="name"
        class="form-control"
        placeholder="Name"
        v-model="user.name"
        required
      />
      </div>

      <div class="item" >
      <button id="create-account" class="button" type="submit">
        Create Account
      </button>
      </div>

      <div class="item" id="need-account-div">
      <router-link class="need-account" :to="{ name: 'login' }">Have an account?</router-link>
      </div>


      </div>
    </form>
  
  <div class="image">
  <img src="https://slack-imgs.com/?c=1&o1=ro&url=https%3A%2F%2Fi.pinimg.com%2F736x%2Fcc%2F79%2F01%2Fcc790126a249a812f0750679b7937334.jpg"/>
  </div>


  </div>

</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: '',
        name: ''
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.create-account{
  font-size: 50px;
  padding-top: 23px;
}

.item{
  margin-bottom: 2%;
}

.background {
  background-color: #e6bc8d;
}

#create-account{
  font-size:25px;
}

.need-account{
  font-size: 20px;
}

#need-account-div{
  margin-bottom: 10px;
}


</style>
