<script setup lang="ts">
import _ from "lodash"
import type {Entry} from "~/types";
import {API_URL} from "~/utils/api";

const props = withDefaults(defineProps<{
  id?: string;
  title?: string;
  body?: string;
  journalId?: string;
}>(), {
  title: "",
  body: "",
})

const title = ref(props.title)
const content = ref(props.body)
const journalId = ref(props.journalId)
const wordCount = ref(0)
const hasUnsavedChanges = ref(false)
const lastSaved = ref(new Date())
const lastSavedContent = ref("")
const saveStatus = ref("")

const route = useRoute()
const router = useRouter()
const {coords} = useGeolocation()

const prompts = ref([])
const isLoading = ref(false)
const promptTheme = ref("");

const generatePrompts = async () => {
  isLoading.value = true;
  prompts.value = await $fetch(`${API_URL}/prompts`, {
    params: {
      theme: promptTheme.value,
    }
  });
  isLoading.value = false;
}

const saveEntry = async (text: string) => {
  // TODO: Use more robust path checking
  // TODO: Also update the entry when the title is changed
  switch(true) {
    case route.path.startsWith("/entries/create"):
      const response: Entry = await $fetch(`${API_URL}/journals/${journalId.value}`, {
        method: "POST",
        body: {
          title: title.value,
          body: content.value,
          latitude: coords.value.latitude,
          longitude: coords.value.longitude,
        }
      })

      const entryId = response.entryId;
      navigateTo(`/entries/${entryId}`, {
        replace: true,
        external: false
      })
      break;
    case route.path.startsWith("/entries"):
      await $fetch(`${API_URL}/entry/${props.id}`, {
        method: "POST",
        body: {
          title: title.value,
          body: content.value,
        }
      })
      break;
    default:
      console.error("This shouldn't be happening");
  }
}

const debouncedSave = _.debounce(async (text) => {
  if (text === lastSavedContent.value) {
    return; // only update if text has changed
  }

  saveStatus.value = "Saving..."

  try {
    await saveEntry(text)
    lastSaved.value = new Date()
    hasUnsavedChanges.value = false
    lastSavedContent.value = text
    saveStatus.value = "Saved"
  } catch (error) {
    saveStatus.value = "Failed to save"
  }
}, 1500)

watch(content, (newContent) => {
  if (newContent !== lastSavedContent.value) {
    hasUnsavedChanges.value = true
    debouncedSave(newContent)
  }
})

watch(content, () => {
  wordCount.value = content.value.trim() ? content.value.trim().split(/\s+/).length : 0
}, {immediate: true})
</script>

<template>
  <div class="flex justify-between -mt-6">
    {{wordCount}} Words
    [{{saveStatus}}]
    <button class="bg-amber-500 rounded px-1 shadow" @click="router.back()">Back</button>
  </div>
  <input v-model="title" type="text" class="block w-full border-0 h-10 my-3 pl-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600" placeholder="Entry Title" />
  <textarea
      v-model="content"
      name="entry"
      id="entry"
      placeholder="Entry Text"
      class="block w-full h-96 rounded-md text-xl border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 pl-2 resize-none"/>

  <form class="mt-2" @submit.prevent="generatePrompts">
    <div class="flex items-center">
      <label for="theme">Prompt Theme: </label>
      <input
          v-model="promptTheme"
          class="border border-black rounded ml-1 pl-1"
          name="theme"
          type="text"/>
    </div>
    <div class="flex items-center">
      <svg class="size-6" fill="none" stroke="currentColor" stroke-width="{1.5}" viewBox="0 0 24 24"
           xmlns="http://www.w3.org/2000/svg">
        <path
            d="M9.813 15.904 9 18.75l-.813-2.846a4.5 4.5 0 0 0-3.09-3.09L2.25 12l2.846-.813a4.5 4.5 0 0 0 3.09-3.09L9 5.25l.813 2.846a4.5 4.5 0 0 0 3.09 3.09L15.75 12l-2.846.813a4.5 4.5 0 0 0-3.09 3.09ZM18.259 8.715 18 9.75l-.259-1.035a3.375 3.375 0 0 0-2.455-2.456L14.25 6l1.036-.259a3.375 3.375 0 0 0 2.455-2.456L18 2.25l.259 1.035a3.375 3.375 0 0 0 2.456 2.456L21.75 6l-1.035.259a3.375 3.375 0 0 0-2.456 2.456ZM16.894 20.567 16.5 21.75l-.394-1.183a2.25 2.25 0 0 0-1.423-1.423L13.5 18.75l1.183-.394a2.25 2.25 0 0 0 1.423-1.423l.394-1.183.394 1.183a2.25 2.25 0 0 0 1.423 1.423l1.183.394-1.183.394a2.25 2.25 0 0 0-1.423 1.423Z"
            stroke-linecap="round"
            stroke-linejoin="round"/>
      </svg>
      <button class="text-3xl ml-2" type="submit">Generate Prompts</button>
    </div>
  </form>

  <div v-if="isLoading">
    Loading...
  </div>
  <div v-if="prompts && !isLoading">
    <ul>
      <li v-for="(prompt, index) in prompts" :key="index">
        {{ prompt }}
      </li>
    </ul>
  </div>
</template>