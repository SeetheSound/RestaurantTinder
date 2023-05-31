<template>
<!-- trying to get a link to connect me to the restaurant-finalists page -->
  <div class='events'>
    <div>
        <h1>My Events</h1>
        <div id="event-list-div" v-for="event in events" :key="event.eventId">
            <h2 id="event-name">{{event.eventName}}</h2>
            When?:
            <p id="event-date">{{event.eventDate}}</p>
            Where?:
            <p id="event-location">{{event.location}}</p>
            RSVP deadline:
            <p id="event-deadline">{{event.rsvpDeadline}}</p>
            <router-link id="router-link" v-bind:to="{name: 'finalist-list', params:{eventId: event.eventId}}">Event Details</router-link>
        </div>
    </div>
  </div>
</template>

<script>
import EventService from '../services/EventService.js'
import moment from 'moment'
export default {
    name: 'events',
    data() {
        return {
            events: [],
        }
    },
    methods: {
        getEventDetails(event){
            this.router.push({name: 'finalist-list', params: {eventId: event.target.value} })
        },
    },
    computed: {
        eventDateConversion(){
            let eventDate = moment(this.$store.state.event.eventDate).format('MM-DD-YYYY', 'hh:mm:A')
            return eventDate;
        }
    },
    
    created() {
        EventService.getEventByUsername(this.$store.state.user.username)
        .then(response => {
            this.events = response.data
        
        })
    }
}
</script>

<style>
#event-name, #event-name, #event-date{
    color: white;
}
#router-link{
    color: white
}


</style>