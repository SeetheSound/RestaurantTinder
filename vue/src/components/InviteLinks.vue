<template>
  <div>
        <form v-on:submit.prevent="generateInviteLink" >
            <div class="form-element">
                <label for="numberOfInvitees">Please enter the number of desired guests: (20 Max) </label>
                <input type="text" class="invitees" v-model="invitees" />
            </div>
            <div v-for="(index) in inviteeInputs" :key="index" class="form-element">
                    <label :for="'inviteLink-' + index">Guest Email: </label>
                    <input :id="'inviteLink-' + index" type="text" v-model="guestEmail[index]" @blur="checkDuplicates(index)" />
                </div>
            <div class="form-element">
                <label for="submit">Generate Links: </label>
                <input id="submit" type="submit"/>
            </div>
        </form>
        <div class="inviteLinks">
            <div v-for="link in this.inviteLinks" v-bind:key="link">
                <a v-bind:href="`http://${link}`">{{link}}</a> 

                <!-- router-link, pass in params {Name: 'inviteVote', params: {eventId: '', email: ''}-->
                <!-- <h3>"Guest " + {{ this.inviteLinks.indexOf(link) + 1 }} + ": " + {{ link }}</h3> -->
            </div>
        </div>
        <!--Find a way to generate a unique link for voting that leverages the eventID.
            When the user clicks on the link, it queries the API with the location and returns a list of
            Restaurants sorted by distance & limited to 10(or something)
            Pull eventID from route for link generation
            Also pull invitee list from the data store, have a forEach method that iterates over them and 
            creates the invite links for them-->
  </div>
</template>

<script>
import EventService from '../services/EventService'
export default {
    data() {
        return {
            invitees: 0,
            inviteLinks: [],
            guestEmail: []
        }
    },
    computed: {
    inviteeInputs() {
      if (!this.invitees || isNaN(parseInt(this.invitees))) {
        return []
      }
      return Array.from({ length: parseInt(this.invitees) }, (_, index) => index)
    },
  },
    methods: {
        checkDuplicates(index) {
            const currentValue = this.guestEmail[index];
            for (let i = 0; i < this.guestEmail.length; i++) {
                if (i !== index && this.guestEmail[i] === currentValue) {
                    alert('Duplicate email address entered!');
                    this.guestEmail[index] = '';
                    return;
                }
            }
        },
        generateInviteLink() { 
            this.inviteLinks = [];
            const guests = this.invitees;

            if (guests <= 20) {

                const guestBody = { 
                    eventId: this.$route.params.eventId,
                    emails: this.guestEmail
                };
                EventService.addGuest(guestBody)
                for (let i = 0; i < guests; i++) {
                    const email = this.guestEmail[i]
                    this.inviteLinks.push("localhost:8080/event/" + this.$route.params.eventId + "/" + email);
                }             
            } else {
                alert("There is a maximum of only 20 guests.")
            }
        }
    }
}

</script>

<style>

</style>