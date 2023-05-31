import axios from 'axios';
export default {

    createEvent(eventBody) {
        // alert(eventBody.eventName);
        return axios.post('/api/event', eventBody);
    },
    getEventById(eventId) {
        return axios.get(`/api/event/${eventId}`)
    },
    getEventByUsername(username) {
        return axios.get(`/api/event/host/${username}`)
    },
    addGuest(guestBody) {
        return axios.post('/api/event/guests', guestBody);
    },
    getEventDetails(eventId) {
        return axios.get(`/api/event/${eventId}`);
    },
    voteForRestaurant(voteBody) {
        return axios.post('/api/vote', voteBody);    
    },
    addCommentToRestaurant(voteBody) {
        return axios.post('/api/vote/comments', voteBody);    
    },
}