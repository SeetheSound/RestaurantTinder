<template>
  <div class="restaurant-search">
      <h1>Look for restaurants near you!</h1>
      <form v-on:submit="searchRestaurants()">
          <div class="form-element">
              <label for="searchTerm">Search by Zip Code, Address, City, or State: </label><br/>
              <input id="searchTerm" type="text" v-model="location" />
          </div>
          <div class="form-element">
              <input id="submit" type="submit"/>
          </div>
      </form>
  </div>
</template>

<script>
export default {
    name: "restaurant-search",
    data() {
        return {
            location: ""
        }
    },
    methods: {
        searchRestaurants() {
            const searchTerm = this.location;
            this.$store.commit("SET_SEARCH_TERM", this.location);
            this.$router.push({name: "restaurant-list", params: {searchTerm: searchTerm}});
        }
    },
    created() {
        const searchTerm = this.$route.params.searchTerm;
        this.$store.commit("SET_SEARCH_TERM", searchTerm);
    }
}
</script>

<style>

</style>