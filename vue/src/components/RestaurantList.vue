<template>
  <div class="restaurant-list">
    <!-- MAKE A WAY TO SORT placed the sort feature outside of the cards class so it can sit on top of the cards -->
    <!-- placed the sort section outside of the cards div so it sits on top of the of cards -->
    <div class="sort-box">
      <label for="sortBy" id="sort-by-label">Sort By: </label>
      <select id="sortBy" v-model="sortType" v-on:change="handleSort($event)">
        <option value="distance" selected>Distance</option>
        <option value="name">Name</option>
        <option value="category">Category</option>
        <option value="rating">Rating</option>
      </select>

      <select
        id="sortDirection"
        v-model="sortDirection"
        v-on:change="handleSort($event)"
      >
        <option value="ascending" selected>Ascending</option>
        <option value="descending">Descending</option>
      </select>
    </div>

    <div class="cards">
      <restaurant-card
        class="card"
        v-for="restaurant in this.$store.state.restaurants"
        v-bind:restaurantCard="restaurant"
        v-bind:key="restaurant.id"
      >
      </restaurant-card>
    </div>
  </div>
</template>

<script>
import RestaurantCard from '../components/RestaurantCard.vue'
import RestaurantService  from '../services/RestaurantService.js'
export default {
    name: 'restaurant-list',
    props: ['list'],
    data() {
        return {
            title: "",
            errorMessage: "",
            sortType: "",
            sortDirection: 'ascending',
            restaurants: "this.$state.store.restaurants"
        }
    },
    components: {
        RestaurantCard
    },
    methods: {
        retrieveRestaurants() {
            let searchTerm = this.$store.state.searchTerm;
            if (searchTerm === '') {
                searchTerm = this.$route.params.searchTerm;
            }
            RestaurantService.getRestaurants(searchTerm).then(response=> {
                this.title = response.data.title
                // alert(response.data);
                this.$store.commit("SET_RESTAURANTS", response.data);
            }).catch(error => {
                if(error.response && error.response.status === 404) {
                    alert("No results found");
                }
            });
        },
        handleSort(event) {
            switch(event.target.value) {
                case 'distance': 
                    this.sortByDistance();
                break;
                case 'name':
                    this.sortByName();
                break;
                case 'category':
                    this.sortByCategory();
                break;
                case 'rating':
                    this.sortByRating();
                break;
                default: 
                break;
            }
            if (event.target.value === 'ascending' || event.target.value === 'descending') {
                let restaurants = this.$store.state.restaurants;
                restaurants = restaurants.reverse();
                this.$store.commit("SET_RESTAURANTS", restaurants);
            } else if (this.sortDirection === 'ascending') {
                let restaurants = this.$store.state.restaurants;
                restaurants = restaurants.reverse();
                this.$store.commit("SET_RESTAURANTS", restaurants);
            }
        },
        viewCardDetails(cardID) {
            alert("Router")
            this.$router.push(`/details/${cardID}`)
        },
        sortByName() { //For location, use the distance element in the restaurant object
            let restaurants = this.$store.state.restaurants;
            restaurants.sort((restaurantA, restaurantB) => {
                return restaurantA.name.localeCompare(restaurantB.name);
            });
            this.$store.commit("SET_RESTAURANTS", restaurants);
        },
        sortByCategory() {
            let restaurants = this.$store.state.restaurants;
            restaurants.sort((restaurantA, restaurantB) => {
                return restaurantA.categories[0].title.localeCompare(restaurantB.categories[0].title);
            });
            this.$store.commit("SET_RESTAURANTS", restaurants)
        },
        sortByRating() {
            let restaurants = this.$store.state.restaurants;
            restaurants.sort((restaurantA, restaurantB) => {
                return restaurantA.rating - restaurantB.rating;
            });
            this.$store.commit("SET_RESTAURANTS", restaurants)
        },

        sortByDistance() {
            let restaurants = this.$store.state.restaurants;
                restaurants.sort((restaurantA, restaurantB) => {
                    return restaurantA.distance - restaurantB.distance;
                });
            this.$store.commit("SET_RESTAURANTS", restaurants)
        }
    },
    created() {
        this.retrieveRestaurants();
    }
};
</script>

<style scoped>
/* this is the container that holds both the sort section and the cards  */
.restaurant-list {
  display: grid;

  grid-template-areas:
    "sort"
    "content";

  grid-template-columns: auto;
  grid-auto-rows: auto;

  justify-content: center;
  background-color: #ffffff3a;
  max-width: 300%;
  align-content: center;
}

/* This is another container specifically for the sort section */
.sort-box {
  grid-area: sort;
  display: flex;

  display: grid;

  grid-template-areas: "sort sortby sortDirection";

  grid-template-columns: 10% 10% 10%;
  grid-template-rows: auto;
  gap: 10px;
  height: 30px;
  margin: 10px;     
}

/* This is the start of the section that allows you to adjust the indivdual sort elements */
.sort-by-label {
  grid-area: sort;
  display: flex;
  text-justify: center;
  text-align: center;

  width: auto;
}

.sortBy {
  grid-area: sortBy;
  display: flex;
  justify-content: center;
}

.sortDirection {
  grid-area: sortDirection;
  display: flex;
  justify-content: flex-end;
}
/* This is the end of the sort section */

/* This is the start of the card section that allows you to adjust the individual elements of the restaurant list container*/
.cards {
  grid-area: content;
  justify-content: center;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  width: fit-content;
}

.restaurantImage {
  max-height: 50px;
  max-width: 50px;
}

.card {
  width: 30%;
  height: auto;
}
/* This is the end */
</style>