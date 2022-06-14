# global rightclick actions
execute if score @s exp_rightclick matches 1.. run function expansion:global/global_rightclick

# custom player damage, Credits to Cloudwolf (https://www.youtube.com/watch?v=5qtR8ozjSb8)
function expansion:handy_tools/custom_damage/main

# store players position
function expansion:global/position

# vehicle functions
function expansion:vehicles/rocket/rocket_tick
function expansion:vehicles/spaceship/spaceship_tick
function expansion:vehicles/buggy/buggy_tick
function expansion:vehicles/lunar_module/lunar_module_tick
execute if entity @s[tag=landing_earth] run function expansion:vehicles/return_capsule/return_capsule_tick

# equipment modules
execute if predicate expansion:nbt_checks/armor/space_equipment/has_module run function expansion:items/space_equipment/modules/modules_tick

# detect in which dimension the player is
function expansion:global/detect_dimension

# delete gui items
clear @s minecraft:jigsaw{gui_item:1b}

# death fix function
execute if score @s exp_death matches 1.. run function expansion:global/player_death

# cooldown utility
scoreboard players remove @s[scores={exp_cooldown=1..}] exp_cooldown 1
execute if score @s exp_warmup matches 1.. run function expansion:global/global_warmup/warmup_loop

# reset all the detection scoreboards
scoreboard players set @s[scores={exp_jump=1..}] exp_jump 0
