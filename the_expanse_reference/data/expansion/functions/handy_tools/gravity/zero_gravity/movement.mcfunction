execute unless score @s[predicate=expansion:utility/sneak] sneak_delay matches 1.. run scoreboard players set @s sneak_delay 4
# run movement functions when shifting/double shifting
tag @s[predicate=expansion:utility/sneak,scores={sneak_delay=..3},x_rotation=-90..-0.1] add rising
tag @s[predicate=expansion:utility/sneak,scores={sneak_delay=..3},x_rotation=0..90] add falling
# remove delay value
scoreboard players remove @s[predicate=!expansion:utility/sneak,scores={sneak_delay=1..}] sneak_delay 1

execute if entity @s[scores={sneak_delay=4},x_rotation=-90..-0.1] run function expansion:handy_tools/gravity/zero_gravity/ascend
execute if entity @s[scores={sneak_delay=4},x_rotation=0..90] run function expansion:handy_tools/gravity/zero_gravity/descend