# increase fuel scoreboard
execute if entity @p[nbt={Inventory:[{id:"minecraft:coal"}]}] if score @s rocket_fuel_lvl < @s rocket_fuel_max run scoreboard players add @s rocket_fuel_lvl 1

# gather more coal title
title @p subtitle {"text":" "}
execute unless entity @p[nbt={Inventory:[{id:"minecraft:coal"}]}] if score @s rocket_fuel_lvl < @s rocket_fuel_max run title @p title {"text":"Gather more coal","color":"red","bold":true}

# remove coal
execute if score @s rocket_fuel_lvl <= @s rocket_fuel_max run clear @p minecraft:coal 1

# calculate fuel percentage
function expansion:handy_tools/rocket_fuel_prct

# run actionbar functions
#execute as @s[scores={rocket_fuel_prct=50..100}] run title @p actionbar ["",{"text":"Fuel: "},{"text":"\u25c0","color":"green"},{"score":{"name":"@s","objective":"rocket_fuel_prct"},"color":"green"},{"text":"%\u25b6","color":"green"},{"text":" "}]
#execute as @s[scores={rocket_fuel_prct=10..50}] run title @p actionbar ["",{"text":"Fuel: "},{"text":"\u25c0","color":"gold"},{"score":{"name":"@s","objective":"rocket_fuel_prct"},"color":"gold"},{"text":"%\u25b6","color":"gold"},{"text":" "}]
#execute as @s[scores={rocket_fuel_prct=0..10}] run title @p actionbar ["",{"text":"Fuel: "},{"text":"\u25c0","color":"dark_red"},{"score":{"name":"@s","objective":"rocket_fuel_prct"},"color":"dark_red"},{"text":"%\u25b6","color":"dark_red"},{"text":" "}]

# 100% fuel title
execute as @s[scores={rocket_fuel_prct=100..}] run title @p title {"text":"Fuel level at 100%","color":"green","bold":true}

# remove the fueling tag when the player interrupts fueling
execute unless entity @p[nbt={Inventory:[{id:"minecraft:coal"}]}] run tag @p remove is_fueling_rocket
execute unless entity @p[distance=..4] run tag @p remove is_fueling_rocket
tag @p[tag=!inside_rocket] remove is_fueling_rocket
execute as @s[scores={rocket_fuel_prct=100..}] run tag @p remove is_fueling_rocket

# enable the scoreboard trigger
scoreboard players enable @p rocket_optns
