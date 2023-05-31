<template>   
    <div class="restaurantList" v-on:click="viewCardDetails(restaurantCard.id)">
        <div class="card" v-bind:key="restaurantCard.id" v-on:click="viewRestaurantDetails(restaurantCard.id)"> 
            <div class="Name">
                <h3  class="heading3-size">{{ restaurantCard.name }}</h3>
            </div>
            <!--Switched the position of the image and name for flow of content  -->
            <div class="image">
                <img v-bind:src='restaurantCard.image_url'/>
            </div>

            <div class="Categories">
                <h4 class="heading4-size">{{ restaurantCard.categories[0].title }}</h4>
            </div>
            <div class="Location">
                <p>{{ restaurantCard.location.display_address[0] }} <br/> {{ restaurantCard.location.display_address[1] }}</p>
            </div>
             <div class="Rating">
                <h4  class="heading4-size">Rating: {{ restaurantCard.rating }}</h4>
            </div>
            <div class="Price">
                <h4  class="heading4-size" v-if="checkPrice()">Price: {{ restaurantCard.price }}</h4>
            </div>
           <div class="Phone-Number">
                <p id="Para">{{ restaurantCard.display_phone }}</p>
            </div> 
           
            
           
            
            

        </div>
    </div> 
</template>

<script>
// import RestaurantService from '../services/RestaurantService.js'
export default {    
    name: 'restaurant-card',
    data() {
        return {
            title: "",
            errorMessage: "",
            sortType: "",
            restaurantDetails: {}
        }
    },
    props: ['restaurantCard'],
    methods: {

        viewRestaurantDetails(cardID) {
            this.$store.commit("SET_ACTIVE_RESTAURANT", cardID);
            this.$router.push(`/details/${cardID}`);
        },
        checkPrice() {
            if (this.restaurantCard.price === null) {
                return false;
            } else {
                return true;
            }
        }
    }, 
}
</script>

<style scoped>

/* This grid container allows you to adjust the contents of the indivudual cards the sizing is kind of off since I did not spend enough time working on it */
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
/* this is the end */


</style>