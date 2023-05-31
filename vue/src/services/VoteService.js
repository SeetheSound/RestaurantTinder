import axios from 'axios'
export default {
    //get restaurant list in order of highest to lowest votes by {eventID}
    getFinalistVotes(eventID){
        return axios.get(`/api/vote/${eventID}/finalists`);
    }
}