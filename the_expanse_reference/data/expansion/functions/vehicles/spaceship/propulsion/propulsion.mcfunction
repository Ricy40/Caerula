effect give @s minecraft:levitation 1 255 true

execute as @s[scores={spaceship_speed=1..,exp_y=..256}] if block ^ ^ ^2 #expansion:expansion_air if block ^ ^ ^1 #expansion:expansion_air run function expansion:vehicles/spaceship/propulsion/tp_forward
execute as @s[scores={exp_y=250..}] run function expansion:vehicles/spaceship/propulsion/tp_forward

execute if predicate expansion:nbt_checks/armor/spaceship run function expansion:vehicles/spaceship/engine_particles/spaceship
execute if predicate expansion:nbt_checks/armor/the_boston run function expansion:vehicles/spaceship/engine_particles/the_boston
execute if predicate expansion:nbt_checks/armor/bomber run function expansion:vehicles/spaceship/engine_particles/bomber

