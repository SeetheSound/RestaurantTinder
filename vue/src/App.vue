<template>
 <div class="container">
    <header>
      <pageHead></pageHead>
    </header>

    <nav class="sidebar-container">
      <!-- place navigation inside -->
      <h3 class="navigation-menu"><div> Menu </div></h3>
        <ul class="menu-list">
          <div class="link">
            <li><router-link class="link-element" v-bind:to="{name: 'home'}">Home</router-link></li> <!-- Will need to be router link -->
            <li><router-link class="link-element" v-bind:to="{name: 'restaurant-search'}">Search</router-link></li>
            <li><router-link class="link-element" v-bind:to="{name: 'create-event'}">Host Event</router-link></li>
            <li><router-link class="link-element" v-bind:to="{name: 'event-list'}">My Events</router-link></li>
            <li><router-link class="link-element" v-bind:to="{name: 'logout'}">Logout</router-link></li>
          </div>
        </ul>
    </nav>

    <div class="main">
      <!-- place content inside -->
       <router-view id="view"/>
    </div>

    <aside>

    </aside>

    <footer>
    <Footer></Footer>
    </footer>
  </div>
</template>

<script>
// import Navigation from './components/Nav'
import PageHead from './components/Header'

export default {
  components: {
    // Navigation,
    PageHead
  }
}
</script>

<style>
/*  This is where you will do the styling on the panels and use the whole flexbox/grid layout 
    Page will be a grid.
    Nav is FlexBox - Left
    Header = FlexBox - Top
    Main = (Dependent on View) 

    Grid Layout:
        Nav Header Header (20, 40, 40)
        Nav Content Content (20, 40, 40)
        Footer Footer Footer (20, 40, 40) 
      Nav & Header will overlap and share space, so similar styling elements need to be applied to both!
      */

     .container{
        display: grid;

        grid-template-areas:
            "header header header"
            "nav content side"
            "footer footer footer";

        grid-template-columns: 200px 1fr 200px;
        grid-template-rows: 125px 1fr 85px;
        gap: 10px;

        height: 100vh;


    }

    #nav {
      grid-area: nav;
      background-color: #A4C2F4;
      grid-area: nav;
      display: flex;
      text-decoration: none;
    }
    header {
       backdrop-filter: blur(16px) saturate(180%);
    -webkit-backdrop-filter: blur(16px) saturate(180%);
    background-color: #d3d3d350;
    border-radius: 12px;
    border: 1px solid #d3d3d350;
      grid-area: header;
      display: flex;
      justify-content: center;
      border-radius: 25px;
    }

    html {
      background-image: url('../public/powder.png');
      background-attachment: fixed;
      height: 100%;
    }

    .content {
      grid-area: content;
    }

    nav.sidebar-container{
       backdrop-filter: blur(16px) saturate(180%);
    -webkit-backdrop-filter: blur(16px) saturate(180%);
    background-color: #d3d3d350;
    border-radius: 12px;
    border: 1px solid #d3d3d350;
      grid-area: nav;
      text-align: center;
      justify-items: center;
      border-radius: 25px;
      text-decoration: none;
    }

    .main{
      backdrop-filter: blur(16px) saturate(180%);
    -webkit-backdrop-filter: blur(16px) saturate(180%);
    background-color: #d3d3d350;
    border-radius: 12px;
    border: 1px solid #d3d3d350;

      grid-area: content;
       text-align: center;
      justify-items: center;
      align-content: center;
      height: 100%;
      width: 100%;
      display: block;
      margin-left: auto;
      margin-right: auto;
      margin-top: auto;
      margin-bottom: auto;
      border-radius: 25px;
    }
    aside {
       backdrop-filter: blur(16px) saturate(180%);
    -webkit-backdrop-filter: blur(16px) saturate(180%);
    background-color: #d3d3d350;
    border-radius: 12px;
    border: 1px solid #d3d3d350;
    grid-area: side;
    border-radius: 25px;
    }

    .navigation-menu {
      justify-content: center;
      align-content: center;
      display: block;

    }

    .menu-list {
      text-align: center;
      display: block;
      list-style-type: none;

    }

    .app {
       display: grid;
      grid-template-areas:
        "header header header"
        "nav content content"
        "footer footer footer";
      grid-template-columns: 1fr 2fr 2fr;
      grid-template-rows: 1fr 2fr 2fr;
      height: 100%;
      width: 100%;
    }

    .sidebar-container {
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    .menu-list {
      list-style-type: none;
      padding: 0;
      margin: 0;
      display: flex;
      flex-direction: column;
      align-items: center;
    }

  body {
        font-family: 'Raleway', sans-serif;
        font-size: 16px;
        font-weight: 400;
    }
  .link {
    display: flex;
    flex-wrap: nowrap;
    flex-direction: column;
    margin-top: 30px;
    align-items: center;
    gap: 50px;
  }
  .link .link-element {
      background-color: white; 
      padding: 10px; 
      border-radius: 15px;
      text-decoration: none;
  }
</style> 
