execute if entity @s[tag=launchpad_good,scores={rocket_fuel_prct=1..,launch_timer=1..230}] if entity @p[predicate=expansion:nbt_checks/armor/space_equipment/equipment] run function expansion:vehicles/rocket/countdown

execute if score @s rocket_fuel_prct matches 50.. run title @p actionbar ["",{"translate":"exp_screentxt_position_actionbar"},{"text":"\u25c0"},{"score":{"name":"@p","objective":"exp_x"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_y"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_z"}},{"text":"\u25b6     "},{"translate":"exp_screentxt_fuel_actionbar"},{"text":"\u25c0","color":"green"},{"score":{"name":"@s","objective":"rocket_fuel_prct"},"color":"green"},{"text":"%\u25b6","color":"green"},{"text":" "}]
execute if score @s rocket_fuel_prct matches 10..50 run title @p actionbar ["",{"translate":"exp_screentxt_position_actionbar"},{"text":"\u25c0"},{"score":{"name":"@p","objective":"exp_x"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_y"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_z"}},{"text":"\u25b6     "},{"translate":"exp_screentxt_fuel_actionbar"},{"text":"\u25c0","color":"green"},{"score":{"name":"@s","objective":"rocket_fuel_prct"},"color":"green"},{"text":"%\u25b6","color":"green"},{"text":" "}]
execute if score @s rocket_fuel_prct matches ..10 run title @p actionbar ["",{"translate":"exp_screentxt_position_actionbar"},{"text":"\u25c0"},{"score":{"name":"@p","objective":"exp_x"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_y"}},{"text":"\u25b6 \u25c0"},{"score":{"name":"@p","objective":"exp_z"}},{"text":"\u25b6     "},{"translate":"exp_screentxt_fuel_actionbar"},{"text":"\u25c0","color":"green"},{"score":{"name":"@s","objective":"rocket_fuel_prct"},"color":"green"},{"text":"%\u25b6","color":"green"},{"text":" "}]

execute if entity @s[scores={launch_timer=0,rocket_fuel_prct=1..}] run function expansion:vehicles/rocket/propulsion

scoreboard players add @s[scores={rocket_fuel_lvl=1..,launch_timer=0}] fuel_timer 1
scoreboard players remove @s[scores={fuel_timer=8..,launch_timer=0}] rocket_fuel_lvl 1
scoreboard players set @s[scores={fuel_timer=8..,launch_timer=0}] fuel_timer 0

function expansion:handy_tools/rocket_fuel_prct