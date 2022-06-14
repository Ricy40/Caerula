execute if score #reload delay matches 125 run function expansion:global/dimension_test
execute if score #reload delay matches 1..120 in expansion:space run function expansion:destinations/space/generation_test/schedule
scoreboard players remove #reload delay 1