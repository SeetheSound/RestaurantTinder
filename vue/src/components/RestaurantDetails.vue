<template>
<!-- Need to show  !!!!!MAKE A WAY TO SORT!!!!!-->
  <div>
    <!-- <h1 class="title">Restaurant Details</h1> -->
    <div class="restaurantDetails">
      <h1 class="restaurant-name">{{ $store.state.restaurantDetails.name }} </h1>

      <div class="image">
        <img v-bind:src='$store.state.restaurantDetails.photos[0]'/>
      </div>
      
      <h3>Restaurant Type:  {{ $store.state.restaurantDetails.categories[0].title }} </h3>

      <h3>Rating: {{ $store.state.restaurantDetails.rating }} Star(s)</h3>

      <div class="Price">
                <h4 v-if="checkPrice()">Price: {{ $store.state.restaurantDetails.price }}</h4>
                <h4 v-else>This Restaurant Has No Pricing Listed.</h4>
      </div>
      
      <h2 class="restaurant-isClosed" v-if="checkHours($store.state.restaurantDetails.hours[0].open[0].start, this.$store.state.restaurantDetails.hours[0].open[0].end)"></h2>

      <h2 v-else> "This Restaurant Has No Hours Listed" </h2>
      
      <img class="restaurant-logo"/>
    </div>
    <h3>
      <p v-show="displayStatus" class="hour-status"> {{ getHourStatus() }} Til {{ startTime }} </p>
      <p v-show="!displayStatus" class="hour-status">{{ getHourStatus() }} Til {{ endTime }}</p>
      
    
    </h3>
    {{ startTime }} - {{endTime}}
    <h3> Call to Order <button class="phone">{{ $store.state.restaurantDetails.phone}}</button></h3>

  </div>
</template>

<script>
import restaurantService from "../services/RestaurantService.js"
import moment from 'moment'
//import RestaurantCard from "../components/RestaurantCard.vue"

export default {
  name: 'restaurant-details',
  data(){
    return{
      restaurant: {},
      days: {

      },
      seen: false

    }
  },
  methods: {
    retrieveRestaurantDetails() {
      restaurantService.getRestaurantDetails(this.$route.params.id)
      .then(response => { 
        this.$store.commit("SET_ACTIVE_RESTAURANT", response.data.id);
        this.restaurant = response.data;
        this.$store.commit("SET_RESTAURANT_DETAILS", response.data);
      });
    },
    checkPrice() {
            if (this.restaurant.price === null) {
                return false;
            } else {
                return true;
            }
    },

    checkHours(hourOpen, hourClose) {
  
      if(hourOpen === '0' || hourClose === '0') {
        this.$store.commit("SET_OPEN_STATUS", false);
        return false; 
      } else {
        this.$store.commit("SET_OPEN_STATUS", true);
        return true;
      }
    },
// something is wrong with this logic. I had to reverse the returns in order to get the results. 
//I did not have to do that originally. Orignally it was Open Open Closed, and I got the desired result
//need to go over this again.
    getHourStatus(){
      if (this.openTime <= this.currentTime && this.closeTime > this.currentTime) {
        return 'Open';
      } else if (this.currentTime >= this.openTime || this.currentTime < this.endTime) {
        return 'Open';
      } else {
        return 'Closed';
      }
    },
    displayStatus(){
      this.closeTime = 'Closed'
      this.openTime = 'Open'
    },

    getYelpDayOfWeek(){
      //moment --> yelp
      const weekDays = new Map([
        [0, 6], 
        [1, 0], 
        [2, 1], 
        [3, 2], 
        [4, 3], 
        [5, 4], 
        [6, 5]
        ]);
        return weekDays;
    }

  },
  created(){
    this.retrieveRestaurantDetails();
  },
  
  computed: {
    //use moment to convert this.$store.state.restaurantstart to regular time. property name= computed start time.
    //create a property called standard close time.
    startTime(){
      const openTime = moment(this.$store.state.restaurantDetails.hours[0].open[0].start, 'HH:mm').format('hh:mm:A')
      return openTime
     // return this.$store.state.restaurantDetails.hours[0].open[0].start;
    },
    endTime(){
      const closeTime = moment(this.$store.state.restaurantDetails.hours[0].open[0].end, 'HH:mm').format('hh:mm:A') 
      return closeTime;    
      // return this.$store.state.restaurantDetails.hours[0].open[0].end;
    },
    currentTime(){
      const now = new Date();
      return moment(now.gethours).format("hh:mm:A")
    },
    currentDay(){
      let today = moment().day()
      return this.getYelpDayOfWeek().get(today) ;
    }
  }
  // hours can stay [0]. 
  // method 

};
</script>

<style scoped>
img {
  max-width: 300px;
  max-height: 300px;
}
</style>