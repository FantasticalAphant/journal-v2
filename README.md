# Journaling App

This journaling application allows you to create journal entries grouped by journals.
You can also create tags to further group entries together by category.
It also provides a map view that allows you to view your entries by location.
There is also a calendar view so you can review your entries by date.

This web application is built using Nuxt (Vue) for the frontend and Spring for the backend.

### Setting up environment variables:

```sh
# you can also just add variables to docker environment
$ vim path/to/src/main/resources/secrets.properties
```

```env
spring.ai.openai.api-key=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
spring.ai.openai.chat.options.model=gpt-4o-mini
```

[OpenAI API Information](https://platform.openai.com/docs/quickstart)

---

## Running on Docker

```sh
$ docker-compose up --build # in the root directory
```