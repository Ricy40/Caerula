execute if entity @a[predicate=expansion:dimension/space,tag=!generator] in expansion:space run function expansion:destinations/space/space_tick
execute if entity @a[predicate=expansion:dimension/moon] in expansion:moon run function expansion:destinations/moon/moon_tick
execute if entity @a[predicate=expansion:dimension/mars] in expansion:mars run function expansion:destinations/mars/mars_tick
execute if entity @a[predicate=expansion:dimension/venus] in expansion:venus run function expansion:destinations/venus/venus_tick
execute if entity @a[predicate=expansion:dimension/asteroids] in expansion:asteroids run function expansion:destinations/asteroids/asteroids_tick
#execute if entity @a[predicate=expansion:dimension/mercury] in expansion:mercury run function expansion:destinations/mercury/mercury_tick
execute if entity @a[predicate=expansion:dimension/jupiter] in expansion:jupiter run function expansion:destinations/jupiter/jupiter_tick
execute if entity @a[predicate=expansion:dimension/europa] in expansion:europa run function expansion:destinations/europa/europa_tick

execute as @a[gamemode=!creative,gamemode=!spectator,scores={expansion_dim=3..},tag=!generator,tag=!inside_rocket,tag=!inside_buggy,tag=!inside_spaceship,tag=!inside_module,tag=!landing_moon] run function expansion:global/oxygen_regulation/oxygen_tick

# creating a new dimension:
# add the worldgen files
# add a new dimension predicate
# update function dimension_tick, detect_dimension, transitions_main
# add new spaceship transitions
# add a new destination folder and tick function
# update the space generation folders generation_test, generation_tick, space_tick, start_generation and add a new planet structure
# add a new go_to command