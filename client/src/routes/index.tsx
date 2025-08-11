import { createFileRoute } from "@tanstack/react-router";
import { z } from "zod";
import SearchPage from "../pages/SearchPage";

const searchSchema = z.object({
  query: z.string().optional().catch(""),
});

export const Route = createFileRoute("/")({
  component: SearchPage,
  validateSearch: searchSchema,
});
