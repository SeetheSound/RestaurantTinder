import axios from 'axios';
export default {
  
// Get Restaurant By Location
// Get Restaurant By ID
  getRestaurants(location){ 
    // alert(location);
    return axios.get(`/api/restaurant/${location}`);
  },
  
  getRestaurantDetails(restaurantID){
    // alert(restaurantID);
    return axios.get(`/api/restaurant/details/${restaurantID}`);
  }
}