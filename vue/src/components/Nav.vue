<template>
  <div class="component" v-if="$store.state.token != ''">
    <div class="Expand/Hide">
      <button v-on:click="expandNav()">Placeholder</button>
    </div>

    <div class="nav-elements">
        <ul class="nav-panel" v-show="this.isExpanded">
          <div class="nav-flex">
            <li><router-link class="link" v-bind:to="{name: 'home'}">Home</router-link></li> <!-- Will need to be router link -->
            <li><router-link class="link" v-bind:to="{name: 'restaurant-search'}">Search</router-link></li>
            <li><router-link class="link" v-bind:to="{name: 'create-event'}">Host Event</router-link></li>
            <li><router-link class="link" v-bind:to="{name: 'event-list'}">My Events</router-link></li>
            <li><router-link class="link" v-bind:to="{name: 'logout'}">Logout</router-link></li>
          </div>
        </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isExpanded: false
    }
  },
  methods: {
    expandNav() {
      this.isExpanded = (!this.isExpanded);
      // return this.isExpanded;
    }
  }
}
</script>

<style>
  .nav-elements {
    text-decoration: none;
  }
  li {
    text-decoration: none;
  }
  a {
    text-decoration: none;
  }
  .nav-flex {
    display: flex;
    flex-wrap: nowrap;
    flex-direction: column;
    align-items: center;
    gap: 10px;
  }
  .nav-flex .link {
     background-color: white; 
      padding: 10px; 
      border-radius: 15px;
      text-decoration: none;
  }

</style>