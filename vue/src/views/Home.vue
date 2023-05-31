<template>
  <div class="home">

      <h1>Dish Date</h1>

      <p>Welcome to Dish Date!</p>
      <!-- <p>{{ $store.state.user.username}}</p> -->
      <div class="main-text">
          <h2>What is Dish Date?</h2>
          <p class="text">Dish date is a service designed to solve the age-old question of "What are we going to have for dinner tonight?" 
             Dish date allows you to search any restaurants near you by a location, and even allows you to host an event and bring along your friends! And the best part? Your friends can even vote on the restaurants! 
          </p>
      </div>
      <div class="link">
        <router-link class="link-element" to="/list"> Click here to begin your search! </router-link> 
        <router-link class="link-element" to="/event/create">Click here to create an event!</router-link> 
        <router-link class="link-element" v-bind:to="{name: 'event-list', params:{username: $store.state.user.username}}">Click here to view your events!</router-link>
      </div>

  </div>
</template>

<script>
// import BackgroundImage from '/Users/Student/workspace/final-capstone-java-team-0/vue/public/TreyArt.png'
// import Home from '../components/Home.vue'
// import Footer from '../components/Footer.vue'
// import Header from '../components/Header.vue'
// import Nav from '../components/Nav.vue'

export default {
  name: "home",
  components: {
    // Home
  }
};
</script>

<style scoped>
  .home {
    display: flex;

  }
  .link {
    display: flex;
    flex-wrap: nowrap;
    flex-direction: column;
    align-items: center;
    gap: 30px;
  }
  .link .link-element {
      background-color: white; 
      padding: 10px; 
      border-radius: 15px;
      text-decoration: none;
  }
  .main-text {
    display: flex;
    flex-direction: column;
    width: 100%;
    align-items: center;
    justify-content: center;
    padding: 0px;
  }
  .main-text .text {
    width: 70%;
    margin-bottom: 20px;
    margin-top: 0px;
  }
</style>