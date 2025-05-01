# ======== build ========
FROM node:20-alpine AS builder
WORKDIR /app
COPY . .
RUN npm ci && npm run build

# ======== runtime ========
FROM nginx:1.27-alpine
COPY --from=builder /app/dist/ /usr/share/nginx/html
EXPOSE 80
