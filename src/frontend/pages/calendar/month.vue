<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import MonthViewCalendar from "~/components/MonthViewCalendar.vue";
</script>

<template>
  <MainShell
      title="Calendar"
      :path="$route.path"
  >
    <MonthViewCalendar/>
  </MainShell>
</template>

<style scoped>

</style>
