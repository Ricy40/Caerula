# run movement functions if the player is wearing the space equipment
execute if entity @s[predicate=expansion:nbt_checks/armor/space_equipment/equipment,tag=!inside_vehicle] run function expansion:handy_tools/gravity/zero_gravity/movement

# apply rising tag when jumping
tag @s[scores={exp_jump=1..},predicate=!expansion:nbt_checks/armor/magnetic_boots] add rising

# apply levitation 255 when correct conditions are met
execute unless predicate expansion:utility/sneak if entity @s[tag=!rising,tag=!falling] if block ~ ~-1 ~ #expansion:expansion_air run function expansion:handy_tools/gravity/zero_gravity/apply_levitation

# run no-friction simulations when rising or falling
execute as @s[tag=rising] run function expansion:handy_tools/gravity/zero_gravity/rising
execute as @s[tag=falling] run function expansion:handy_tools/gravity/zero_gravity/falling

# make all other entities float
execute as @e[type=!player,distance=..50,limit=1,sort=nearest] unless entity @s[nbt={NoGravity:1b}] run data merge entity @s {NoGravity:1b}