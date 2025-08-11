import { defineConfig } from "orval";

export default defineConfig({
  backend: {
    input: {
      target: "./openapi.yml",
    },
    output: {
      target: "./src/api/backend.ts",
      clean: true,
      client: "react-query",
    },
  },
});
