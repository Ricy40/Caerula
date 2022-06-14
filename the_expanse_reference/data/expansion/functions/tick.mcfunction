# post load functions
execute if score #reload delay matches 1..140 run function expansion:global/post_load

# generate planets
execute unless score #total_system gen_success matches 1 as @a[tag=generator] in expansion:space run function expansion:destinations/space/generation_tick

# the global timer
execute unless score global exp_timer_1 matches 0..999 run scoreboard players set global exp_timer_1 0
scoreboard players add global exp_timer_1 1

# run functions depending on the dimension you are in
execute if entity @a[scores={expansion_dim=3..}] run function expansion:global/global_dimension

# run functions looping as and at players
execute as @a at @s run function expansion:global/global_player

# mob damage
execute as @e[type=#expansion:sentient,scores={exp_damage=1..},limit=1] run function expansion:handy_tools/custom_damage/damage_mob

# mobs
#execute as @e[tag=expansion_mob] at @s run function expansion:mobs/mobs_tick
#execute as @e[type=item,nbt={Item:{id:"minecraft:jigsaw",tag:{death_item:1b}}},limit=1] at @s run function expansion:mobs/mob_death

# blocks
execute as @e[type=armor_stand,tag=expansion_block] at @s run function expansion:global/global_blocks
execute as @e[type=item,predicate=expansion:nbt_checks/items/steel8] at @s run function expansion:blocks/crafter/create

# guide1
execute as @e[type=item,predicate=expansion:nbt_checks/items/book,limit=1] at @s if block ~ ~-1 ~ minecraft:smithing_table run function expansion:.commands/give_guide1

# kill gui items
kill @e[type=item,predicate=expansion:nbt_checks/items/gui,limit=1]