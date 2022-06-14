function expansion:handy_tools/gravity/zero_gravity/main
#execute if entity @s[gamemode=!creative,gamemode=!spectator,tag=!inside_rocket,tag=!inside_spaceship,tag=!inside_module] run function expansion:global/temperature_regulation/cold_planet

execute if entity @e[type=armor_stand,tag=earth,distance=..100] run function expansion:global/transitions/from_space/space_to_earth
execute if entity @e[type=armor_stand,tag=moon,distance=..100] run function expansion:global/transitions/from_space/space_to_moon
execute if entity @e[type=armor_stand,tag=venus,distance=..100] run function expansion:global/transitions/from_space/space_to_venus
execute if entity @e[type=armor_stand,tag=mercury,distance=..100] run function expansion:global/transitions/from_space/space_to_mercury
execute if entity @e[type=armor_stand,tag=mars,distance=..100] run function expansion:global/transitions/from_space/space_to_mars
execute if entity @e[type=armor_stand,tag=jupiter,distance=..100] run function expansion:global/transitions/from_space/space_to_jupiter
execute if entity @e[type=armor_stand,tag=europa,distance=..100] run function expansion:global/transitions/from_space/space_to_europa

execute if predicate expansion:location/space_edge run function expansion:global/transitions/from_space/space_to_asteroids
