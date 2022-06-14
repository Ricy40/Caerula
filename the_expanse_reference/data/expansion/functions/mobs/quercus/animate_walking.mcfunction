execute unless score @s exp_timer_1 matches ..11 run scoreboard players set @s exp_timer_1 0
scoreboard players add @s exp_timer_1 1

data modify entity @s[scores={exp_timer_1=1}] ArmorItems[3].tag.CustomModelData set value 125002
data modify entity @s[scores={exp_timer_1=4}] ArmorItems[3].tag.CustomModelData set value 125004
data modify entity @s[scores={exp_timer_1=7}] ArmorItems[3].tag.CustomModelData set value 125003
data modify entity @s[scores={exp_timer_1=10}] ArmorItems[3].tag.CustomModelData set value 125004
