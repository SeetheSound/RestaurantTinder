<template>
    <div class="restaurant-list">
      <div><center>To vote on a restaurant, simply click on the thumbs up or thumbs down icon at the bottom of the card. Feel free to add comments!</center>
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
      </div>
      
      

      <div class="cards">
            <div class="cardRestaurantList" v-for="restaurant in this.$store.state.restaurants" :key="restaurant.id">
              <div class="card" v-bind:key="restaurant.id"> 
                  <div class="Name">
                      <h3  class="heading3-size">{{ restaurant.name }}</h3>
                  </div>
                  <div class="image">
                      <img v-bind:src='restaurant.image_url'/>
                  </div>

                  <div class="Categories">
                      <h4 class="heading4-size">{{ restaurant.categories[0].title }}</h4>
                  </div>
                  <div class="Location">
                      <p>{{ restaurant.location.display_address[0] }} <br/> {{ restaurant.location.display_address[1] }}</p>
                  </div>
                  <div class="Rating">
                      <h4  class="heading4-size">Rating: {{ restaurant.rating }}</h4>
                  </div>
                <div class="Phone-Number">
                      <p id="Para">{{ restaurant.display_phone }}</p>
                  </div> 
                  <div class="VoteThumb">
                    <a href="#" @click="thumbsUp(restaurant.id, true)">Thumbs Up  <span v-if="isSubmittingUp[restaurant.id]" class="spinner"><img src="../../public/loading.gif"/></span></a>
                    <a href="#" @click="thumbsUp(restaurant.id, false)">Thumbs Down <span v-if="isSubmittingDown[restaurant.id]" class="spinner"><img src="../../public/loading.gif"/></span> </a>
                  </div>
                  <div v-if="showDone[restaurant.id]" class="done-message">Your vote was recorded!</div>
                  <input placeholder="Comments?" type="text" v-model="userComments[restaurant.id]" @blur="executeFunction(restaurant.id, userComments[restaurant.id])" />  <span v-if="isSubmittingComment[restaurant.id]" class="spinner"><img src="../../public/loading.gif"/></span>
                   <div v-if="showDoneComment[restaurant.id]" class="done-message">Your comment was recorded!</div>
              </div>
          </div> 
        </div>
          
        
    </div>
      
  
</template>



<script>
// import RestaurantCard from '../components/RestaurantCard.vue'
import eventService from '../services/EventService.js'
import RestaurantService  from '../services/RestaurantService.js'


export default {
    props: ['list'],
    data() {
        return {
            title: "",
            errorMessage: "",
            sortType: "",
            restaurants: "this.$state.store.restaurants",
            eventDetails: {},
            eventId: "",
            isSubmittingUp: {},
            isSubmittingDown: {},
            showDone: {},
            userComments: {} , 
            isSubmittingComment: {},
            showDoneComment: {},
            sortDirection: 'ascending'
        }
    },
    // components: {
    //     RestaurantCard
    // },
  mounted() {
    this.eventId = this.$route.params.eventId;
    this.email = this.$route.params.inviteeEmail;
    console.log("Mount" + this.eventId)
    console.log("Mount" + this.email)
  },
  methods: {
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
        },
          checkPrice() {
                  if (this.restaurantCard.price === null) {
                      return false;
                  } else {
                      return true;
                  }
        },
        retrieveRestaurants() {
          console.log(this.$route.params.eventId);
          eventService.getEventDetails(this.$route.params.eventId).then(response => {
            this.eventDetails = response.data;
            console.log(this.eventDetails.location); // move the console.log here
            RestaurantService.getRestaurants(this.eventDetails.location).then(response=> {
                this.title = response.data.title
                this.$store.commit("SET_RESTAURANTS", response.data);
            }).catch(error => {
                if(error.response && error.response.status === 404) {
                    alert("No results found");
                }
            });
          })

        },
        thumbsUp(restaurantId, thumbDirection) {
          event.preventDefault(); 
          const voteBody = { eventId: this.$route.params.eventId,
                              guestEmail: this.$route.params.inviteeEmail,
                              yelpRestaurantId: restaurantId,
                              vote: thumbDirection
                                    };
                                    console.log(voteBody)
                                    if(thumbDirection === true) {
                                      this.$set(this.isSubmittingUp, restaurantId, true);
                                    } else {
                                      this.$set(this.isSubmittingDown, restaurantId, true);
                                    }
          eventService.voteForRestaurant(voteBody).then(() => {
            setTimeout(() => {
              this.$set(this.isSubmittingDown, restaurantId, false);
              this.$set(this.isSubmittingUp, restaurantId, false);
              this.$set(this.showDone, restaurantId, true);

              setTimeout(() => {
                this.$set(this.showDone, restaurantId, false);
              }, 2000);
            }, 1000);
          }).catch( () => {
            // handle the error
          })
        },
        executeFunction(restaurantId, comment) {
          console.log(`User comment for restaurant ${restaurantId}: ${comment}`);
          this.$set(this.isSubmittingComment, restaurantId, true);
          event.preventDefault(); 
          const voteBody = { eventId: this.$route.params.eventId,
                              guestEmail: this.$route.params.inviteeEmail,
                              yelpRestaurantId: restaurantId,
                              guestComments: comment
                                    };
          eventService.addCommentToRestaurant(voteBody).then(() => {
            setTimeout(() => {
              this.$set(this.isSubmittingComment, restaurantId, false);
              this.$set(this.showDoneComment, restaurantId, true);
              setTimeout(() => {
                this.$set(this.showDoneComment, restaurantId, false);
              }, 2000);
            }, 1000);
          }).catch( () => {
            // handle the error
          })
        },
        
      },
      created() {
        this.retrieveRestaurants();
    },
}
</script>

<style scoped>
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
.cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  grid-area: content;
  gap: 10px;
  width: fit-content;
}

.cardRestaurantList {
  width: calc(33.33% - 10px);
  margin-bottom: 20px;
}
/* .card img {
  width: 200px;
  height: 200px;
} */
.card {
    backdrop-filter: blur(16px) saturate(180%);
    -webkit-backdrop-filter: blur(16px) saturate(180%);
    background-color: hsla(224, 83%, 44%, 0.041);
    border-radius: 12px;
    border: 1px solid #3b71c44d;

    height: 100%;
    width: auto;

    display: grid;

    grid-template-areas: 
    "Name"
    "image"
    "location"
    "rate price"
    "number";

    grid-template-columns: auto;
    grid-template-rows: auto;
    gap: 3px;
}

.Name {
    display: flex;
    justify-content: center;
}

/* This is the start of the individual components of the grid container */
.image {
    display: flex;
    justify-content: center;
    height: auto;
    margin: 1%;
    padding: 41px;
}

.Categories {
    display: flex;
    justify-content: center;
}

.Location {
    display: flex;
}

.Rating {
    display: flex;
}

.Price {
    display: flex;
}

.Phone-Number{
    display: flex;
}

.heading4-size{
    margin: 1%;
}

.heading3-size{
    margin: 1%;
}

.Para {
    margin: 1%;
}


img {
    max-width: 250px;
    max-height: 250px;
}

.spinner img {
  width: 16px;
  height: 16px;
}
</style>