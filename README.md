# Journaling App

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