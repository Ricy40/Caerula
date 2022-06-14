scoreboard players set @s[scores={exp_timer_1=20}] exp_timer_1 0
scoreboard players add @s exp_timer_1 1

execute if score @s exp_timer_1 matches 20 unless score @s exp_steel_lvl matches 124446.. run function expansion:blocks/arc_furnace/melt_iron/melt

execute unless entity @s[tag=burning] run function expansion:blocks/arc_furnace/switch_model/turn_on