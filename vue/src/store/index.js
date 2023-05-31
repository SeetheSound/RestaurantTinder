import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    searchTerm: '',
    restaurants: [],
    sortValue: 'Ascending',
    invitees: [],
    activeRestaurant: null,
    events: [],
    newEvent: {
      eventName: '',
      description: '',
      eventDate: '',
      rsvpDeadline: '',
      location: ''
    },
    finalistVotes: [],
    restaurant: {
      id: '',
      name: '',
      image_url: '',
      url: '',
      review_count: '',
      categories: [{title: ''}, {title: ''}, {title: ''}],
      rating: '',
      price: '',
      location: {
        address1: '',
        address2: '',
        address3: '',
        city: '',
        zip_code: '',
        country: '',
        state: '',
        display_address: ['','']
      },
      display_phone: '',
      distance: ''
    },
    days: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
    restaurantDetails: {}
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
    //       console.log(token)
    // throw new Error('Wuh oh Log-in Error')
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
          // console.log(user)
          // throw new Error('Wuh oh Log-in Error')
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_INVITEES(state, invitees) {
      state.invitees = invitees;
    },
    SET_EVENT_ID(state, id) {
      state.createEventNewEventId = id;
    },
    CREATE_EVENT(state, event) {
      state.newEvent = event;
    },
    SET_EVENT(state, event) {
      state.events = event;
    },
    SET_RESTAURANTS(state, restaurants) {
      state.restaurants = restaurants;
    },
    SET_RESTAURANT_DETAILS(state, restaurantDetails) {
      state.restaurantDetails = restaurantDetails;
    },
    SET_ACTIVE_RESTAURANT(state, restaurantID) {
      state.activeRestaurant = restaurantID;
    },
    SET_SEARCH_TERM(state, location) {
      state.searchTerm = location;
    },
    SET_SORT_VALUE(state, sortValue) {
      state.sortValue = sortValue;
    }
  },
  getters: {
    restaurant(state) {
      return state.restaurants.find(r => r.id == state.activeRestaurant)
    }
  }
})
