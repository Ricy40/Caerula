execute as @s[tag=inside_rocket,predicate=expansion:dimension/overworld] at @s run function expansion:global/transitions/moon_earth/earth_to_moon

execute as @s[tag=inside_module,predicate=expansion:dimension/moon] at @s run function expansion:global/transitions/moon_earth/moon_to_earth

execute as @s[tag=inside_spaceship,predicate=expansion:dimension/overworld] at @s run function expansion:global/transitions/to_space/earth_to_space
execute as @s[tag=inside_spaceship,predicate=expansion:dimension/moon] at @s run function expansion:global/transitions/to_space/moon_to_space
execute as @s[tag=inside_spaceship,predicate=expansion:dimension/mars] at @s run function expansion:global/transitions/to_space/mars_to_space
execute as @s[tag=inside_spaceship,predicate=expansion:dimension/venus] at @s run function expansion:global/transitions/to_space/venus_to_space
execute as @s[tag=inside_spaceship,predicate=expansion:dimension/asteroids] at @s run function expansion:global/transitions/to_space/asteroids_to_space
#execute as @s[tag=inside_spaceship,predicate=expansion:dimension/mercury] at @s run function expansion:global/transitions/to_space/mercury_to_space
execute as @s[tag=inside_spaceship,predicate=expansion:dimension/jupiter] at @s run function expansion:global/transitions/to_space/jupiter_to_space
execute as @s[tag=inside_spaceship,predicate=expansion:dimension/europa] at @s run function expansion:global/transitions/to_space/europa_to_space

advancement revoke @s only expansion:utility/space_transition