<template>
<div>
  <!-- working on getting the restaurant details to show once the page is able to be navigated to. -->
  <div class="finalist-list">
      <h1>Your guests chose:</h1>
      <div id="finalist-list-div" v-for="restaurant in FinalistVotes" :key="restaurant.restaurantYelpId">
        <p>
          {{restaurant.restaurantDetails.name}} {{restaurant.netVoteCount}}
        </p>
      </div>
  </div>
</div>
</template>

<script>
import VoteService from '../services/VoteService.js'


export default {
    name: 'finalist-comp',
    data(){
      return{
      FinalistVotes:[]
      }
    },

  components: {
    
  },

  methods:{
    displayFinalistList() {
      VoteService.getFinalistVotes(this.$route.params.eventId)
      .then(response => {
        this.FinalistVotes = response.data;
      })
      .catch(error => {
        console.error(error);
      })
    }

  },
   created(){
//    VoteService.getFinalistVotes(this.$store.state.event.FinalistVotes)
  this.displayFinalistList();
   }
  
}
</script>

<style>
div{
  color: white;
}

</style>