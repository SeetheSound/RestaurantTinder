import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import RestaurantList from '../views/RestaurantList.vue'
import RestaurantSearch from '../views/RestaurantSearch.vue'
import Restaurant from '../views/Restaurant.vue'
import CreateEvent from '../views/CreateEvent.vue'
// import InviteeList from '../views/InviteeList.vue'
import InviteLinks from '../views/InviteLinks.vue'
import EventList from '../views/EventList.vue'
import InviteeVote from '../views/InviteeVote.vue'
import FinalistList from '../views/FinalistList.vue'


// import NotFound from '../views/NotFound.vue'
//Views are incorporated in router*// 

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true 
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/list/:searchTerm',
      name: 'restaurant-list',
      component: RestaurantList,
      meta: {
        requiresAuth: true //!!!TEMPORARY CHANGE!!!//
      }
    },
    {
      path: '/list',
      name: 'restaurant-search',
      component: RestaurantSearch,
      meta: {
        requiresAuth: true
      }
    },
    //added a new router to go from list of
    //restaurants to one restaurant for details
    {
      path: '/details/:id',
      name: 'restaurant-details',
      component: Restaurant,
      meta: {
        requiresAuth: true
      },
    },
    {
      path: '/event/create',
      name: 'create-event',
      component: CreateEvent, 
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/event/:eventId', 
      name: 'invite-links',
      component: InviteLinks,
      meta: {
        requiresAuth: true
      }
    },
    // {
    //   path: '/event/:eventId/:inviteeId',
    //   name: 'invitee-id',
    //   component: InviteeList,
    //   meta: {
    //     requiresAuth: false
    //   }
    // },
    {
      path: '/event/:eventId/:inviteeEmail',
      name: 'invitee-vote',
      component: InviteeVote,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/event/host/:username',
      name: 'event-list',
      component: EventList,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/vote/:eventId/finalists',
      name: 'finalist-list',
      component: FinalistList,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
