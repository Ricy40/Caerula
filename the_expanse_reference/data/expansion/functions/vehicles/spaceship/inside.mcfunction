# apply spaceship potion effects
function expansion:vehicles/spaceship/inside_effects

# adjust the speed depending on the selected itemslot and change the dashboard accordingly
execute store result score @s spaceship_speed run data get entity @s SelectedItemSlot
title @p[scores={fuel_percentage=50..}] actionbar ["",{"translate":"exp_screentxt_position_actionbar"},{"text":"\u25c0"},{"score":{"name":"@p","objective":"exp_x"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_y"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_z"}},{"text":"\u25b6     "},{"translate":"exp_screentxt_speed_actionbar"},{"text":"\u25c0"},{"score":{"name":"@p","objective":"spaceship_speed"}},{"text":"/8\u25b6     "},{"translate":"exp_screentxt_fuel_actionbar"},{"text":"\u25c0","color":"green"},{"score":{"name":"@s","objective":"fuel_percentage"},"color":"green"},{"text":"%\u25b6","color":"green"},{"text":" "}]
title @p[scores={fuel_percentage=10..50}] actionbar ["",{"translate":"exp_screentxt_position_actionbar"},{"text":"\u25c0"},{"score":{"name":"@p","objective":"exp_x"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_y"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_z"}},{"text":"\u25b6     "},{"translate":"exp_screentxt_speed_actionbar"},{"text":"\u25c0"},{"score":{"name":"@p","objective":"spaceship_speed"}},{"text":"/8\u25b6     "},{"translate":"exp_screentxt_fuel_actionbar"},{"text":"\u25c0","color":"gold"},{"score":{"name":"@s","objective":"fuel_percentage"},"color":"gold"},{"text":"%\u25b6","color":"gold"},{"text":" "}]
title @p[scores={fuel_percentage=..10}] actionbar ["",{"translate":"exp_screentxt_position_actionbar"},{"text":"\u25c0"},{"score":{"name":"@p","objective":"exp_x"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_y"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_z"}},{"text":"\u25b6     "},{"translate":"exp_screentxt_speed_actionbar"},{"text":"\u25c0"},{"score":{"name":"@p","objective":"spaceship_speed"}},{"text":"/8\u25b6     "},{"translate":"exp_screentxt_fuel_actionbar"},{"text":"\u25c0","color":"dark_red"},{"score":{"name":"@s","objective":"fuel_percentage"},"color":"dark_red"},{"text":"%\u25b6","color":"dark_red"},{"text":" "}]

# regulates the propulsion
execute as @s[scores={fuel_level=1..}] run function expansion:vehicles/spaceship/propulsion/propulsion

# antidupe
function expansion:vehicles/spaceship/antidupe

# fuel functions 
function expansion:handy_tools/fuel_percentage
scoreboard players add @s[scores={fuel_level=1..}] fuel_timer 1
scoreboard players remove @s[scores={fuel_timer=40..}] fuel_level 1
scoreboard players set @s[scores={fuel_timer=40..}] fuel_timer 0


# marker functions
execute if entity @s[predicate=expansion:dimension/space,tag=markertag1] run function expansion:vehicles/spaceship/markers/markers

# fuel loss upon damage
execute if entity @s[nbt={HurtTime:9s}] run function expansion:vehicles/spaceship/dmg_fuel_loss

# detects whether or not a player holds sneak(this took my tiny brain a long time to make)
execute if predicate expansion:utility/sneak unless score @s sneak_delay matches 20.. run scoreboard players add @s sneak_delay 1
execute if score @s sneak_delay matches 1..19 unless predicate expansion:utility/sneak run scoreboard players remove @s sneak_delay 1
execute if score @s sneak_delay matches 20.. unless predicate expansion:utility/sneak run scoreboard players reset @s sneak_delay

# exit functions depending on environment
execute if predicate expansion:utility/sneak unless block ~ ~-1 ~ #expansion:expansion_air run function expansion:vehicles/spaceship/exit
execute unless predicate expansion:utility/sneak if score @s sneak_delay matches 1..19 if score @s expansion_dim matches 3 run function expansion:vehicles/spaceship/exit
execute if predicate expansion:utility/sneak unless score @s sneak_delay matches 20 if score @s expansion_dim matches 7 run function expansion:vehicles/spaceship/exit