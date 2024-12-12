<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import {LMap, LMarker, LPopup, LTileLayer} from "@vue-leaflet/vue-leaflet";
import 'leaflet/dist/leaflet.css'
import type {Entry} from "~/types";

const {coords} = useGeolocation()
const {data: entries} = await useFetch<Entry[]>("http://localhost:8080/entries");

const zoom = ref(5);
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


        <!--        TODO: group entries with the same coordinates-->
        <LMarker v-for="entry in entries" :key="entry.entryId"
                 :lat-lng="[entry.latitude, entry.longitude]">
          <LPopup>
            <NuxtLink :to="`http://localhost:3000/entries/${entry.entryId}`">
              {{ entry.title }}
            </NuxtLink>
          </LPopup>
        </LMarker>

      </LMap>
    </div>

  </MainShell>
</template>

<style scoped>

</style>
