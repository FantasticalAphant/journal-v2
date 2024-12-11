<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import {LMap, LTileLayer} from "@vue-leaflet/vue-leaflet";
import 'leaflet/dist/leaflet.css'

const {coords} = useGeolocation()

const zoom = ref(8);
</script>

<template>
  <MainShell
      title="Map"
      :path="$route.path">

    <div style="height:75vh; width:75vw">
      <LMap
          ref="map"
          :center="[coords.latitude, coords.longitude]"
          :use-global-leaflet="false"
          :zoom="zoom"
      >
        <LTileLayer
            attribution="&amp;copy; <a href=&quot;https://www.openstreetmap.org/&quot;>OpenStreetMap</a> contributors"
            layer-type="base"
            name="OpenStreetMap"
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
      </LMap>
    </div>

  </MainShell>
</template>

<style scoped>

</style>
