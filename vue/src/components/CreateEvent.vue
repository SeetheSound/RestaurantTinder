<template>
  <div class="create-event">
      <h2 class="header"> Host an event! </h2>
      <div class="event-form">
          <form class="user-input" v-on:submit.prevent="submitForm()">
            <div class="event-name-form-element">
                <label for="eventName">Please enter a title for your event:</label>
                <input id="eventName" type="text" v-model="newEvent.eventName" />
            </div>
            <div class="event-location-form-element">
                <label for="location">Please enter your desired event area (Address, Zip Code, City, etc.)</label>
                <input id="location" type="text" v-model="newEvent.location" />
            </div>
            <div class="event-description-form-element">
                <label for="description">Please enter a description of your event:</label>
                <input id="description" type="textarea" v-model="newEvent.description" />
            </div>
            <div class="event-date-form-element">
                <label for="eventDate">Please enter the date of your event:</label>
                <input id="eventDate" type="date" v-model="newEvent.eventDate"/>
            </div>
            <div class="event-rsvp-form-element">
                <label for="rsvpDeadline">Please enter the date you would like your attendees to RSVP by: </label>
                <input id="rsvpDeadline" type="date" v-model="newEvent.rsvpDeadline" />
            </div>
            <div class="event-submit-form-element">
                <label for="submit">Submit: </label>
                <input id="submit" type="submit"/>
            </div>


          </form>
      </div>
       <!-- Create a form for the eventName, location, the description, event date, and deadline -->
       <!-- Create a way for users to invite others to their event, generate inviteeID upon access of the page
            Generate a list of restaurants based on the location that they give
            Store restaurants for event based on userID?  -->
  </div>
</template>

<script>
import moment from 'moment';
import eventService from '../services/EventService.js'
export default {
    name: 'create-event',
    data() {
        return {
            newEventId: 0,
            newEvent: {
                eventName: '',
                description: '',
                eventDate: '',
                rsvpDeadline: '',
                location: ''
            },
            testEvent: {
                eventName: 'Birthday Party',
                description: 'Come down for my birthday!',
                eventDate: '2023-05-01T18:00:00',
                rsvpDeadline: '2023-04-01T18:00:00',
                location: '19734'
            }
        }
    },
    methods: {
        submitForm() {         
            let eventId = this.newEventId;
            this.newEvent.eventDate = moment(this.newEvent.eventDate);
            this.newEvent.rsvpDeadline = moment(this.newEvent.rsvpDeadline);
            eventService.createEvent(this.newEvent).then(response => {
                eventId = response.data.eventId;
                this.newEventId = eventId;
                this.$router.push(`/event/${eventId}`);
            }).catch(error => {
                alert(error.response.status)
            })

            // this.$router.push("/list");
            // Take response and store the eventID, then use that later to generate inv links
            // Router push to invite page (api/event/:eventId)after this
            // Get eventID, generate links with the eventID and the calculated invitelink(s)
            // alert("submitForm");
        },
        /*
        /   Form is submitted > Create the event and pass the information to the database. (Includes inviteLinks(userId))
        /   Query database with event ID, generate a (limited?) list based off of location and return that list on voting page  
        */
    }
}
</script>

<style>
/* This is the container for the "frosted" box. You can adjust lines 100-99 to change the size and position of the box. Currently it is set be in the middle */
.create-event {
    backdrop-filter: blur(16px) saturate(180%);
    -webkit-backdrop-filter: blur(16px) saturate(180%);
    background-color: hsla(224, 83%, 44%, 0.322);
    border-radius: 12px;
    border: 1px solid #3b71c44d;
    position: absolute;
    margin: auto;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;


    height: fit-content;
    width: fit-content;

    display: grid;

    grid-template-areas:
    "header" 
    "name"
    "location"
    "description"
    "date"
    "rsvp"
    "submit";

    grid-template-columns: auto;
    grid-template-rows: auto;
    gap: 3px;
}

/* This is the begining of the content inside the box */
.header {
    grid-area: header;
    display: flex;
    justify-content: center;
}

.event-name-form-element {
grid-area: name;
display: flex;
justify-content: center;
}

.event-location-form-element {
grid-area: location;
display: flex;
justify-content: center;
}

.event-description-form-element {
grid-area: description;
display: flex;
justify-content: center;
}

.event-date-form-element {
grid-area: date;
display: flex;
justify-content: center;
}

.event-rsvp-form-element {
grid-area: rsvp;
display: flex;
justify-content: center;
}

.event-submit-form-element {
grid-area: submit;
display: flex;
justify-content: center;
}
/* This is the end of the content inside the box */
/*  */
</style>