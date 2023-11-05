-- consulta 1
SELECT first_name, last_name, email, address
FROM customer
JOIN address ON customer.address_id = address.address_id
WHERE city_id = 312;

-- consulta 2
SELECT film.title, film.description, film.release_year, category.name, film.rating, film.special_features
FROM film
JOIN film_category ON film.film_id = film_category.film_id
JOIN category ON film_category.category_id = category.category_id
WHERE category.name = "Comedy";

-- consulta 3
SELECT actor.actor_id, CONCAT(actor.first_name, ' ', actor.last_name) AS actor_name, film.title, film.description, film.release_year
FROM actor
JOIN film_actor ON film_actor.actor_id = actor.actor_id
JOIN film ON film.film_id = film_actor.film_id
WHERE actor.actor_id = 5;

-- consulta 4
SELECT 	customer.first_name, customer.last_name, customer.email, address.address
FROM customer
JOIN address ON customer.address_id = address.address_id
JOIN city ON address.city_id = city.city_id
WHERE customer.store_id = 1 AND city.city_id IN (1, 42, 312, 459); -- SE USA IN PARA EVITAR ESCRIBIR LOS NUMEROS 1X1

-- consulta 5
SELECT film.title, film.description, film.release_year, film.rating, film.special_features
FROM film
JOIN film_actor ON film_actor.film_id = film.film_id
JOIN actor ON film_actor.actor_id = actor.actor_id
WHERE film.rating = "G"
AND film.special_features LIKE '%Behind the scenes%'
AND actor.actor_id = 15;

-- consulta 6
SELECT film.film_id, film.title, actor.actor_id, CONCAT(actor.first_name, " ", actor.last_name) AS actor_name
FROM film
JOIN film_actor ON film_actor.film_id = film.film_id
JOIN actor ON film_actor.actor_id = actor.actor_id
WHERE film.film_id = 369;

-- consulta 7
SELECT film.title, film.description, film.release_year, film.rating, film.special_features, category.name
FROM film
JOIN film_category ON film.film_id = film_category.film_id
JOIN category ON film_category.category_id = category.category_id
WHERE category.name = 'Drama' AND film.rental_rate = 2.99;



-- CONSULTA 8
SELECT film.title, film.description, film.release_year, film.rating, film.special_features, category.name, CONCAT (actor.first_name, " ", actor.last_name) AS actor_name
FROM actor
JOIN film_actor ON film_actor.actor_id = actor.actor_id
JOIN film ON film_actor.film_id = film.film_id
JOIN film_category ON film_category.film_id = film.film_id
JOIN category ON film_category.category_id = category.category_id
WHERE actor.first_name = "Sandra" AND actor.last_name = "Kilmer" AND category.name ="Action";









