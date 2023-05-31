<template>
  <div id="list">
      <!-- This is the main page where the actual list of restaurants would be -->
      <h1>Restaurants Near {{nearLocation}}</h1>
        <!-- List of the restaurant objects -->
        <!-- <restaurant-list></restaurant-list> -->
        <restaurant-list/>
  </div>
</template>

<script>
import RestaurantList from '../components/RestaurantList.vue'

export default {
    components: {
        RestaurantList
    },
    data() {
      return {
        nearLocation: this.$route.params.searchTerm
        
      }
    }
}
</script>

<style scoped>
</style>