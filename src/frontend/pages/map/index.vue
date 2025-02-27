<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import {LMap, LMarker, LPopup, LTileLayer} from "@vue-leaflet/vue-leaflet";
import 'leaflet/dist/leaflet.css'
import type {Entry} from "~/types";
import {API_URL} from "~/utils/api";

const zoom = ref(5);

const {coords, error} = useGeolocation()

const defaultCoords = {latitude: 38.9072, longitude: -77.0369} // Use DC as default coordinates
const currentCoords = computed(() => {
  if (error.value) {
    return defaultCoords
  }
  return coords.value || defaultCoords
})

const {data: entries} = await useFetch<Entry[]>(`${API_URL}/entries`);

const groupedEntries = computed(() => {
  if (!entries.value) return [];
  const groups = new Map();

  entries.value.forEach(entry => {
    const key = `${entry.latitude},${entry.longitude}`;
    const group = groups.get(key) || [];
    group.push(entry);
    groups.set(key, group);
  });

  return Array.from(groups.values());
});

// Update coords when they change
// This hopefully fixes the Infinity coordinates issue
const center = computed<[number, number] | undefined>(() => {
  if (currentCoords.value) {
    return [currentCoords.value.latitude, currentCoords.value.longitude];
  }
  return undefined;
})

</script>

<template>
  <MainShell
      title="Map"
      :path="$route.path">

    <div style="height:75vh; width:75vw">
      <LMap
          ref="map"
          :center="center"
          :use-global-leaflet="false"
          :zoom="zoom"
          :max-zoom="18"
          :min-zoom="3"
          :zoom-animation="true"
      >
        <LTileLayer
            attribution="&amp;copy; <a href=&quot;https://www.openstreetmap.org/&quot;>OpenStreetMap</a> contributors"
            layer-type="base"
            name="OpenStreetMap"
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <LMarker
            v-for="(group, index) in groupedEntries"
            :key="index"
            :lat-lng="[group[0].latitude, group[0].longitude]"
        >
          <LPopup>
            <div class="popup-content">
              <p v-if="group.length > 1" class="font-bold mb-2">
                {{ group.length }} Entries at this location
              </p>
              <ul class="space-y-2">
                <li v-for="entry in group" :key="entry.entryId">
                  <NuxtLink :to="`/entries/${entry.entryId}`">
                    {{ entry.title }}
                  </NuxtLink>
                </li>
              </ul>
            </div>
          </LPopup>
        </LMarker>
      </LMap>

    </div>

  </MainShell>
</template>

<style scoped>

</style>
