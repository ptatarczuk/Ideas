FROM node:18-alpine
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD ["npm", "start"]

# najpierw dodaje package.json, żeby cashować przy kolejnym budowaniu obrazu